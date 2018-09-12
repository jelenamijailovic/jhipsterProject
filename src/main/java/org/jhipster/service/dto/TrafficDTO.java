package org.jhipster.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Traffic entity.
 */
public class TrafficDTO implements Serializable {

    private Long id;

    private String trafficDate;

    private String user;

    private String userId;

    private Long songId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrafficDate() {
        return trafficDate;
    }

    public void setTrafficDate(String trafficDate) {
        this.trafficDate = trafficDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrafficDTO trafficDTO = (TrafficDTO) o;
        if (trafficDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trafficDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrafficDTO{" +
            "id=" + getId() +
            ", trafficDate='" + getTrafficDate() + "'" +
            ", user='" + getUser() + "'" +
            ", userId='" + getUserId() + "'" +
            ", song=" + getSongId() +
            "}";
    }
}
