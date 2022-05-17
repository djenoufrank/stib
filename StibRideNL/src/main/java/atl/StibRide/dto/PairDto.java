/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atl.StibRide.dto;

/**
 *
 * @author g55301
 */
public class PairDto {
    private int id_line;
    private int id_station;

    public PairDto(int id_line, int id_station) {
        this.id_line = id_line;
        this.id_station = id_station;
    }

    public int getId_line() {
        return id_line;
    }

    public void setId_line(int id_line) {
        this.id_line = id_line;
    }

    public int getId_station() {
        return id_station;
    }

    public void setId_station(int id_station) {
        this.id_station = id_station;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id_line;
        hash = 37 * hash + this.id_station;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PairDto other = (PairDto) obj;
        if (this.id_line != other.id_line) {
            return false;
        }
        if (this.id_station != other.id_station) {
            return false;
        }
        return true;
    }
    
}
