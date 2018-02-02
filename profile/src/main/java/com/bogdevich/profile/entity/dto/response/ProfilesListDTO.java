package com.bogdevich.profile.entity.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Simple JavaBean for transfer a list of {@link ProfileResponseDTO}.
 *
 * @author Eugene Bogdevich
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfilesListDTO {
    private int pages;
    private long items;
    private List<ProfileResponseDTO> profiles;

    public ProfilesListDTO() {
    }

    public ProfilesListDTO(int pages, long items, List<ProfileResponseDTO> profiles) {
        this.pages = pages;
        this.items = items;
        this.profiles = profiles;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getItems() {
        return items;
    }

    public void setItems(long items) {
        this.items = items;
    }

    public List<ProfileResponseDTO> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ProfileResponseDTO> profiles) {
        this.profiles = profiles;
    }
}
