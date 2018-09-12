package org.jhipster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Traffic.
 */
@Entity
@Table(name = "traffic")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Traffic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "traffic_date")
    private String trafficDate;

    @Column(name = "jhi_user")
    private String user;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne
    @JsonIgnoreProperties("songs")
    private Song song;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrafficDate() {
        return trafficDate;
    }

    public Traffic trafficDate(String trafficDate) {
        this.trafficDate = trafficDate;
        return this;
    }

    public void setTrafficDate(String trafficDate) {
        this.trafficDate = trafficDate;
    }

    public String getUser() {
        return user;
    }

    public Traffic user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public Traffic userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Song getSong() {
        return song;
    }

    public Traffic song(Song song) {
        this.song = song;
        return this;
    }

    public void setSong(Song song) {
        this.song = song;
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
        Traffic traffic = (Traffic) o;
        if (traffic.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), traffic.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Traffic{" +
            "id=" + getId() +
            ", trafficDate='" + getTrafficDate() + "'" +
            ", user='" + getUser() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }
}
