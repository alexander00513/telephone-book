package com.gmail.bogatyr.alexander.shared.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ContactDto implements Serializable {

    public interface Search {}
    public interface Save {}

    private Long id;
    @NotNull(groups = Search.class)
    @Size(min = 1, max = 64, groups = Search.class)
    private String search;
    @NotNull(groups = Save.class)
    @Size(min = 5, max = 12, groups = Save.class)
    private String number;
    @NotNull(groups = Save.class)
    @Size(min = 3, max = 64, groups = Save.class)
    private String name;
    
    public ContactDto() {
    }

    public ContactDto(String search) {
        this.search = search;
    }

    public ContactDto(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public ContactDto(Long id, String number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactDto)) return false;

        ContactDto that = (ContactDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContactDto{");
        sb.append("id=").append(id);
        sb.append(", number='").append(number).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
