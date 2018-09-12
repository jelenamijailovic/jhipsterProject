package org.jhipster.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Song.
 */
@Entity
@Table(name = "song")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "song")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Traffic> songs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("artists")
    private Artist artist;

    @ManyToOne
    @JsonIgnoreProperties("prices")
    private Price price;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Song name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Traffic> getSongs() {
        return songs;
    }

    public Song songs(Set<Traffic> traffic) {
        this.songs = traffic;
        return this;
    }

    public Song addSong(Traffic traffic) {
        this.songs.add(traffic);
        traffic.setSong(this);
        return this;
    }

    public Song removeSong(Traffic traffic) {
        this.songs.remove(traffic);
        traffic.setSong(null);
        return this;
    }

    public void setSongs(Set<Traffic> traffic) {
        this.songs = traffic;
    }

    public Artist getArtist() {
        return artist;
    }

    public Song artist(Artist artist) {
        this.artist = artist;
        return this;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Price getPrice() {
        return price;
    }

    public Song price(Price price) {
        this.price = price;
        return this;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Song song = (Song) o;
        if (song.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), song.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Song{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
