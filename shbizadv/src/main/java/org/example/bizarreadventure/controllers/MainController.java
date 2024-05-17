package org.example.bizarreadventure.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.bizarreadventure.com.CommentStatus;
import org.example.bizarreadventure.entity.*;
import org.example.bizarreadventure.repository.AnimeRepository;
import org.example.bizarreadventure.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private AnimeService animeService;
    @Autowired
    private  UserRatingService userRatingService;
    @Autowired
    private AnimeGenreService animeGenreService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AnimeRepository animeRepository;
    @Autowired
    private AnimeRepository animeRepository1;
    //User controller start
    @GetMapping("/login")
    public String getLoginPage(Model model){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@RequestParam String login,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String confirm_password,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        Map<String, String> errors = userService.validateUserData(login, email, password, confirm_password);

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "register";
        }
        redirectAttributes.addFlashAttribute("message","Вы успешно зарегистрировались, можете войти.");
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        if (userService.authenticate(login, password)) {
            session.setAttribute("user", userService.getUser(login));
            return "redirect:/profile";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/profile")
    public String userProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            List<UserList> favorites = favoriteService.findFavoritesByUserId(user);
            List<UserMessages> userMessages = userService.findMessagesByUser(user);
            model.addAttribute("favorites", favorites);
            model.addAttribute("usermessages", userMessages);
            userService.updateUserRanking(user, favorites);

            return "profile";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    //User controller end

    //User rating controller start
    @PostMapping("/anime/rating")
    public String rateAnime(@RequestParam("animeId") int animeId,
                            @RequestParam("userId") int userId,
                            @RequestParam("rating") int rating,
                            RedirectAttributes redirectAttrs) {
        Anime anime = animeService.getOneAnime(animeId);
        if (anime == null) {
            redirectAttrs.addFlashAttribute("error", "Аниме не найдено.");
            return "redirect:/anime";
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            redirectAttrs.addFlashAttribute("error", "Пользователь не найден.");
            return "redirect:/anime/" + animeId;
        }

        boolean success = userRatingService.rateAnime(user, anime, rating);
        if (success) {
            redirectAttrs.addFlashAttribute("message", "Рейтинг успешно обновлен.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Не удалось обновить рейтинг.");
        }

        return "redirect:/anime/" + animeId;
    }
    //User rating controller end

    //Genre controller start
    @GetMapping("/genre/{id}")
    public String getAnimePage(@PathVariable(value = "id") int genreId, Model model){
        Genre genre = genreService.getGenre(genreId);
        List<Anime> anime= new ArrayList<>();
        if (genreService.isGenreExists(genreId)) {
            anime = animeGenreService.getAnimeByGenre(genreId);
            genre = genreService.getGenre(genreId);
            if (!anime.isEmpty()) {
                model.addAttribute("animeList", anime);
            }
            if (genre!=null) {
                model.addAttribute("genre", genre);
            }
            return "anime";
        } else {
            return "redirect:/";
        }
    }
    //Genre controller end

    //Comment controller start
    @PostMapping("/anime/comments/{animeId}/{userId}")
    public String saveComment(@PathVariable("userId") int userId,
                              @PathVariable("animeId") int animeId,
                              @RequestParam("comment") String commentText,
                              Model model) {
        try {
            User user = userService.getUserById(userId);
            Anime anime = animeService.getOneAnime(animeId);
            Map<String, String> errors = commentService.validateCommentData(commentText);
            if (!errors.isEmpty()) {
                model.addAttribute("validationErrors", errors);
                return "redirect:/anime/" + animeId + "?error=validationError";
            }

            Comment createdComment = commentService.addComment(user, anime, commentText);
            if (createdComment != null) {
                return "redirect:/anime/" + animeId;
            } else {
                model.addAttribute("error", "Не удалось добавить комментарий");
                return "redirect:/anime/" + animeId + "?error=commentNotAdded";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Произошла непредвиденная ошибка");
            return "redirect:/anime/" + animeId + "?error=unexpectedError";
        }
    }

    @PostMapping("/comment/approve")
    public String approveComment(@RequestParam("commentId") int commentId, RedirectAttributes redirectAttributes) {
        commentService.approveComment(commentId);
        redirectAttributes.addFlashAttribute("message", "Комментарий одобрен.");
        return "redirect:/comment";
    }

    @PostMapping("/comment/reject")
    public String rejectComment(@RequestParam("commentId") int commentId, RedirectAttributes redirectAttributes) {
        commentService.rejectComment(commentId);
        redirectAttributes.addFlashAttribute("message", "Комментарий отклонен.");
        return "redirect:/comment";
    }

    @PostMapping("/comment/delete")
    public String deleteComment(@RequestParam("commentId") int commentId, RedirectAttributes redirectAttributes) {
        commentService.deleteComment(commentId);
        redirectAttributes.addFlashAttribute("message", "Комментарий удален.");
        return "redirect:/comment";
    }

    @GetMapping("/comment")
    public String showComments(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        if ("admin".equals(user.getRole())) {
            model.addAttribute("pendingComments", commentService.findPendingComments());
            model.addAttribute("approvedComments", commentService.findApprovedComments());
            model.addAttribute("rejectedComments", commentService.findRejectedComments());
        } else {
            model.addAttribute("userComments", commentService.findCommentsByUser(user));
        }
        return "comment";
    }

    //Comment controller end

    //Anime genre controller start
    @PostMapping("/anime/{id}/addGenre")
    public String addGenreToAnime(@PathVariable("id") int animeId, @RequestParam int genreId){
        Anime anime = animeService.getOneAnime(animeId);
        if (anime != null && genreId != 0) {
            animeGenreService.addAnimeGenre(genreId, anime);
            return "redirect:/anime/" + animeId;
        } else {
            return "redirect:/anime/" + animeId + "?error=invalidGenre";
        }
    }
    //Anime genre controller end

    //Anime controller start
    @GetMapping("/anime")
    public String getAnimePage(@RequestParam(required = false) String search, Model model){
        List<Anime> animeList = new ArrayList<>();
        if(search!=null&&!search.isEmpty())
            animeList = animeService.getAnimeByName(search);
        else
            animeList=animeService.getAnime();
        model.addAttribute("animeList", animeList);
        return "anime";
    }
    @GetMapping("/")
    public String getMainPage(@RequestParam(required = false) String search, Model model){
        List<Anime> animeList = new ArrayList<>();
        if(search!=null&&!search.isEmpty())
            animeList = animeService.getAnimeByName(search);
        else
            animeList=animeService.getAnime();
        model.addAttribute("animeList", animeList);
        boolean singleton = animeRepository==animeRepository1;
        model.addAttribute("singleton", singleton);
        return "index";
    }
    @GetMapping("/anime/{id}")
    public String showAnimePage(@PathVariable(value = "id") int animeId, Model model, HttpSession httpSession) {
        Anime anime = animeService.getOneAnime(animeId);
        List<AnimeSeries> animeSeries = new ArrayList<>();
        User user = (User) httpSession.getAttribute("user");

        if (animeService.isAnimeExists(animeId)) {
            animeSeries = animeService.getAllSeries(animeId);
            if (!animeSeries.isEmpty()) {
                model.addAttribute("animeseries", animeSeries);
            }

            model.addAttribute("anime", anime);
            model.addAttribute("averageRating", anime.getRating());
            if (user != null) {
                model.addAttribute("user", user);
            }
            List<CommentDTO> comments = commentService.getCommentsByAnimeId(animeId);
            model.addAttribute("comments", comments);
            List<Genre> genres = genreService.getAllGenres();
            if (genres.isEmpty()) {
                System.out.println("No genres found");
            } else {
                model.addAttribute("genres", genres);
            }
            List<AnimeGenreDTO> animeGenres = animeService.getGenresForAnime(animeId);
            model.addAttribute("animeGenres", animeGenres);
            return "single";
        } else {
            return "redirect:/";
        }
    }



    @PostMapping("/anime/addToFavorites")
    public String addToFavorites(@RequestParam("animeId") int animeId, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Anime anime = animeService.getOneAnime(animeId);
            if (anime != null) {
                favoriteService.addToFavorites(user, anime);
                return "redirect:/anime/" + animeId;
            } else {
                redirectAttributes.addFlashAttribute("error", "Аниме с ID " + animeId + " не найдено.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Для добавления аниме в избранное необходимо войти в систему.");
        }
        return "redirect:/login";
    }
    @PostMapping("/anime/subscribe")
    public String subscribe(@RequestParam("anime_id") int anime_id, @RequestParam("user_id") int user_id, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Anime anime = animeService.getOneAnime(anime_id);
            if (anime != null) {
                animeService.subscribeToAnime(user, anime);
                return "redirect:/anime/" + anime_id;
            } else {
                redirectAttributes.addFlashAttribute("error", "Аниме с ID " + anime_id + " не найдено.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Для подписки на аниме необходимо войти в систему.");
        }
        return "redirect:/login";
    }
    @PostMapping("/anime/{id}")
    public String addAnimeSeries(@PathVariable(value = "id") int id, @RequestParam int number, @RequestParam MultipartFile video, Model model) throws IOException {
        Map<String, String> errors = animeService.addAnimeSerie(id, number, video);
        Anime anime = new Anime();
        List<AnimeSeries> animeSeries = new ArrayList<>();
        if(animeService.isAnimeExists(id)){
            animeSeries=animeService.getAllSeries(id);
            if(!animeSeries.isEmpty()){
                model.addAttribute("animeseries", animeSeries);
            }
            anime = animeService.getOneAnime(id);
            model.addAttribute("anime", anime);
        }
        else return "redirect:/index";
        if (errors.isEmpty()) {
            animeService.notifySubscribers(id);
            model.addAttribute("message", "Серия добавлена");
            return "single";
        }
        model.addAttribute("errors", errors);
        return "single";
    }
    //Anime controller end

    //Admin controller start
    @GetMapping("/admin")
    public String getAdminPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user != null && user.getRole().equals("admin")) {
            model.addAttribute("user", user);
            return "admin";
        } else {
            if(user==null)
                return "redirect:/login";
            else return "redirect:/profile";
        }
    }
    @PostMapping("/admin")
    public String createUser(@RequestParam String name,
                             @RequestParam int seriescount,
                             @RequestParam String status,
                             @RequestParam String studio,
                             @RequestParam String typeofanime,
                             @RequestParam String sourceofanime,
                             @RequestParam MultipartFile avatar,
                             @RequestParam MultipartFile background,
                             Model model) throws IOException {
        Map<String, String> errors = animeService.validateAnimeData(name, seriescount, status, studio, typeofanime, sourceofanime, avatar, background);

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "admin";
        }
        model.addAttribute("message", "Аниме добавлено");
        return "admin";
    }

    @PostMapping("/genre")
    public String addGenre(@RequestParam String name,
                           Model model) {
        genreService.addGenre(name);
        model.addAttribute("messagegenre", "Жанр добавлен");
        return "admin";
    }
    //Admin controller end
}
