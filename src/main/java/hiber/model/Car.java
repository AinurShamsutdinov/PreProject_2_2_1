package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column
    private String name;

    @Column
    private int series;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL, mappedBy = "car")
    private User user;

    public Car(){

    }

    public Car(String name, int series){
        this.name = name;
        this.series = series;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setSeries(int series){
        this.series = series;
    }

    public int getSeries(){
        return series;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public String toString(){
        return name + " " + series;
    }
}
