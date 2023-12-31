package com.irisi.facebook.controllers;

import com.irisi.facebook.dto.CommentaireDto;
import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.entities.Commentaire;
import com.irisi.facebook.services.interfaces.CommentaireService;
import com.irisi.facebook.services.interfaces.PosteService;
import com.irisi.facebook.services.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL

@RestController
@RequestMapping("/commentaires")
public class CommentaireController {
    @Autowired
    public CommentaireService commentaireService;
    @Autowired
    private UserService userService;
    @Autowired
    private PosteService posteService;
    @Autowired
    private HttpSession httpSession;

//    @GetMapping("/{commentId}")
//    public ResponseEntity<CommentaireDto> getCommentaire(@PathVariable("commentId") String id) {
//        CommentaireDto commentaireDto=commentaireService.getCommentaire(id);
//        return new ResponseEntity<>(commentaireDto, HttpStatus.OK);
//    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentaireDto>> getAllCommentairesByPostId(@PathVariable("postId") String postId) {
        System.out.println("id de mon poste dans comment " + postId);
        List<CommentaireDto> commentaires = commentaireService.getCommentairesByPostId(postId);
        return new ResponseEntity<>(commentaires, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CommentaireDto> createCommentaire(@RequestBody CommentaireDto commentaireDto) {

        // Récupérer l'utilisateur existant
        UserDto existingUserDto = userService.getUserById(commentaireDto.getUserId());

        // Récupérer le poste existant
        PosteDto existingPosteDto = posteService.getPostById(commentaireDto.getPosteId());

        System.out.println(" mon comment "+commentaireDto.getContenu());
        System.out.println(" user id de  comment "+commentaireDto.getUserId());

        if (existingUserDto != null) {
            Commentaire commentaire = new Commentaire();
            commentaire.setId(commentaireDto.getId());
            commentaire.setContenu(commentaireDto.getContenu());
            commentaire.setDatePublication(commentaireDto.getDatePublication());
            commentaire.setUserId(commentaireDto.getUserId());
            commentaire.setPosteId(commentaireDto.getPosteId());

            // Enregistrer le commentaire
            CommentaireDto createdCommentDto = commentaireService.saveCommentaire(commentaire);

            // Récupérer la liste des commentaires de l'utilisateur
            List<CommentaireDto> comments = existingUserDto.getComments();
            if (comments == null) {
                comments = new ArrayList<>();
            }

            // Ajouter le nouveau commentaire à la liste
            comments.add(createdCommentDto);
            existingUserDto.setComments(comments);

            // Mettre à jour l'utilisateur avec la nouvelle liste de commentaires
            userService.updateUser(commentaireDto.getUserId(), existingUserDto);
            // Récupérer la liste des commentaires du poste
            List<CommentaireDto> commentsOfPoste = existingPosteDto.getCommentaireList();
            if (commentsOfPoste == null) {
                commentsOfPoste = new ArrayList<>();
            }

            // Ajouter le nouveau commentaire à la liste commentaire du post
            commentsOfPoste.add(createdCommentDto);
            existingPosteDto.setCommentaireList(commentsOfPoste);

            // Mettre à jour le poste avec la nouvelle liste de commentaires
            posteService.updatePoste(commentaireDto.getPosteId(), existingPosteDto);


            return new ResponseEntity<>(createdCommentDto, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CommentaireDto>> getAllCommentaires(){
        List<CommentaireDto> commentaires = commentaireService.allCommentaires();
        return new ResponseEntity<>(commentaires,HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable("commentId") String id) {
        commentaireService.deleteCommentaire(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentaireDto> partialUpdateCommentaire( @PathVariable("commentId") String id,
                                                                    @RequestBody CommentaireDto commentaireDto) {

        CommentaireDto updateCommentDto = commentaireService.updateCommentaire(id,commentaireDto);
        return new ResponseEntity<>(updateCommentDto,HttpStatus.OK);
    }

}