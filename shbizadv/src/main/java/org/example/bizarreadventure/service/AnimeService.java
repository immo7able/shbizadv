package org.example.bizarreadventure.service;

import org.example.bizarreadventure.entity.*;
import org.example.bizarreadventure.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository animeRepository;
    @Autowired
    private AnimeSeriesRepository animeSeriesRepository;
    @Autowired
    private SubscribeListRepository subscribeListRepository;
    @Autowired
    private UserMessagesRepository userMessagesRepository;
    @Autowired
    private AnimeGenreRepository animeGenreRepository;
    @Value("${upload.path}")
    private String uploadPath;
    private static final Pattern ALLOWED_CHARACTERS_PATTERN = Pattern.compile("^[a-zA-Z0-9.@_]+$");
    public Map<String, String> validateAnimeData(String name,
                                                 int seriescount,
                                                 String status,
                                                 String studio,
                                                 String typeofanime,
                                                 String sourceofanime,
                                                 MultipartFile avatar,
                                                 MultipartFile background) throws IOException {
        Map<String, String> errors = new HashMap<>();
        if(name.isEmpty()){
            errors.put("name","Имя пустое");
        }
        if(seriescount<=0){
            errors.put("series_count","Недопустимое число");
        }
        if(!status.equals("announcement")&&!status.equals("ongoing")&&!status.equals("completed")){
            errors.put("status",status);
        }
        if (studio.isEmpty()) {
            errors.put("studio", "Студия пуста");
        }
        if (!typeofanime.equals("series")&&!typeofanime.equals("movie")&&!typeofanime.equals("ova")&&!typeofanime.equals("ona")&&!typeofanime.equals("special")){
            errors.put("typeofanime", typeofanime);
        }
        if (sourceofanime.isEmpty()) {
            errors.put("sourceofanime", "Источник пуст");
        }
        if ((avatar == null) || avatar.getOriginalFilename().isEmpty()) {
            errors.put("avatar", "Аватар пуст");
        }
        if ((background == null) || background.getOriginalFilename().isEmpty()) {
            errors.put("background", "Бэкграунд пуст");
        }
        if(animeRepository.existsByName(name)){
            errors.put("name", "Данное имя уже занято");
        }
        if (errors.isEmpty()) {
            Anime anime = new Anime();
            anime.setName(name);
            anime.setSeriesCount(seriescount);
            anime.setStatus(status);
            anime.setStudio(studio);
            anime.setTypeOfAnime(typeofanime);
            anime.setSourceOfAnime(sourceofanime);
            anime.setOutdate(new Date());
            anime.setViews(0);
            anime.setRating(0);
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String UUIDAvatar = UUID.randomUUID().toString();
            String UUIDBackround = UUID.randomUUID().toString();
            String resultAvatarName = UUIDAvatar+"."+avatar.getOriginalFilename();
            String resultBackgroundName = UUIDBackround+"."+background.getOriginalFilename();
            avatar.transferTo(new File(uploadPath+"/"+resultAvatarName));
            background.transferTo(new File(uploadPath+"/"+resultBackgroundName));
            anime.setAvatar(resultAvatarName);
            anime.setBackground(resultBackgroundName);
            animeRepository.save(anime);
        }
        return errors;
    }
    public Map<String, String> addAnimeSerie(int id,
                                                 int number,
                                                 MultipartFile video) throws IOException {
        Map<String, String> errors = new HashMap<>();
        if(number<=0){
            errors.put("number","Недопустимое число");
        }
        if(id<=0){
            errors.put("id","Недопустимое число");
        }
        if ((video == null) || video.getOriginalFilename().isEmpty()) {
            errors.put("video", "Видео пустое");
        }
        if(animeSeriesRepository.existsByNumberAndAnimeid(number, id)){
            errors.put("number", "Данная серия уже добавлена");
        }
        if (errors.isEmpty()) {
            AnimeSeries animeSeries = new AnimeSeries();
            File uploadDir = new File(uploadPath+"/"+id);
            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }
            String resultVideoName = id+"/"+number+"."+video.getContentType().split("/")[1];
            video.transferTo(new File(uploadPath+"/"+resultVideoName));
            animeSeries.setAnimeid(id);
            animeSeries.setNumber(number);
            animeSeries.setVideo(resultVideoName);
            animeSeriesRepository.save(animeSeries);
        }
        return errors;
    }
    public void subscribeToAnime(User user, Anime anime){
        SubscribeList existingSub = subscribeListRepository.findByUserAndAnime(user, anime);
        if (existingSub == null) {
            SubscribeList newSubscribeList = new SubscribeList();
            newSubscribeList.setUser(user);
            newSubscribeList.setAnime(anime);
            subscribeListRepository.save(newSubscribeList);
        }
    }
    public List<AnimeGenreDTO> getGenresForAnime(int animeId) {
        List<AnimeGenre> genres = animeGenreRepository.findByAnimeId(animeId);
        return genres.stream().map(ag -> {
            AnimeGenreDTO dto = new AnimeGenreDTO();
            dto.setName(ag.getGenre().getName());
            return dto;
        }).collect(Collectors.toList());
    }
    private boolean isAllowedCharacters(String input) {
        return ALLOWED_CHARACTERS_PATTERN.matcher(input).matches();
    }
    public boolean isAnimeExists(int anime_id){
        return animeRepository.existsById(anime_id);
    }
    public Anime getOneAnime(int anime_id){
        Optional<Anime> animeOptional = animeRepository.findById(anime_id);
        return animeOptional.orElse(null);
    }
    public List<Anime> getAnime(){
        return animeRepository.findAll();
    }
    public List<AnimeSeries> getAllSeries(int anime_id){
        return animeSeriesRepository.findAllByAnimeidOrderByNumber(anime_id);
    }
    public List<Anime> getAnimeByName(String name){
        return animeRepository.findAllByName(name);
    }

    public void notifySubscribers(int id) {
        Anime anime = getOneAnime(id);
        ArrayList<SubscribeList> subscribeList = subscribeListRepository.findByAnime(anime);
        for(SubscribeList el:subscribeList){
            update(el.getUser(), el.getAnime());
        }
    }
    public void update(User user, Anime anime) {
        UserMessages userMessages = new UserMessages();
        userMessages.setUser(user);
        userMessages.setMessage("Вышла новая серия в аниме "+anime.getName());
        userMessagesRepository.save(userMessages);
    }
}
