package org.jhipster.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Song entity.
 */
public class SongDTO implements Serializable {

    private Long id;

    private String name;

    private Long artistId;

    private Long priceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SongDTO songDTO = (SongDTO) o;
        if (songDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), songDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SongDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", artist=" + getArtistId() +
            ", price=" + getPriceId() +
            "}";
    }
}
