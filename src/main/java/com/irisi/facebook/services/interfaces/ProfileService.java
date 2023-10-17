package com.irisi.facebook.services.interfaces;

import com.irisi.facebook.dto.ProfileDto;
import com.irisi.facebook.entities.Profile;

import java.util.List;

public interface ProfileService {
    ProfileDto saveProfil(ProfileDto profiledto);
    ProfileDto getProfil (String id);
    ProfileDto getProfilByUserId (String userId);
    ProfileDto updateProfil(String id,ProfileDto profileDto);
    void deleteProfil(String id);
    List<ProfileDto> allProfiles();
}
