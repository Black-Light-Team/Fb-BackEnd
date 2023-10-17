package com.irisi.facebook;

import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.dto.ProfileDto;
import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.entities.User;
import com.irisi.facebook.repositories.UserRepository;
import com.irisi.facebook.services.PosteImpl;
import com.irisi.facebook.services.ProfileImp;
import com.irisi.facebook.services.UserImpl;
import org.bson.types.ObjectId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;


@SpringBootApplication
@Slf4j
public class FacebookApplication  {


    public static void main(String[] args) {
        SpringApplication.run(FacebookApplication.class, args);
    }

        @Bean
        CommandLineRunner start(ProfileImp profileImp ){
        return args ->{
            ProfileDto profilDto=new ProfileDto();
            profilDto.setCitation("Never Give Up!");
            profilDto.setStatus("Explorateur du monde \uD83C\uDF0D | Amateur de musique \uD83C\uDFB5 | Amoureux de la vie ❤️ | Partageant des sourires \uD83D\uDE0A");
            profilDto.setUserId("651c3d2ecc85827ed598fba5");
//            posteDto.setContenu("la vie est belle !");
//            posteDto.setDatePublication(new Date(15-10-2023));
//            posteDto.setDislikes(1);
//            posteDto.setLikes(10);
//            posteDto.setUserId("651c3d2ecc85827ed598fba5");
//            posteDto.setUserId("65258dd59d42e05142382c36");
//            userDto.setNom("najwa");
//            userDto.setPrenom("NAJWA");
//            userDto.setAdresseEmail("najwa@test");
////            userDto.setDateNaissance(new Date());
//            userDto.setMotDePasse("123.com");
            log.info("User : "+profileImp.saveProfil(profilDto));
//            log.info("User : "+user.allUsers());
//            log.info("User : "+user.getUser("651bca217ef049489f5083d4"));
//            user.deleteUser("651bd1366d9a2b5330eae4de");
//
//            UserDto userDto = new UserDto();
//            userDto.setId("123");  // Remplacez "123" par l'ID spécifique que vous souhaitez utiliser
//            userDto.setNom("John");
//            userDto.setPrenom("Doe");
//            userDto.setAdresseEmail("john.doe@example.com");
//
//            // Appel de la méthode pour mettre à jour l'utilisateur
////            log.info("Updated User : " + user.updateUser(userDto));
        };
    }
}
