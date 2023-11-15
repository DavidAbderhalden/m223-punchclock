package ch.zli.m223.model;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Entry {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @Column(nullable = false)
  private LocalDateTime checkIn;

  @Column(nullable = false)
  private LocalDateTime checkOut;

  @ManyToOne(optional = false)
  @JsonIgnoreProperties("entries")
  @Fetch(FetchMode.JOIN)
  private Category category;

  @ManyToOne(optional = false)
  @JsonIgnoreProperties("entries")
  @Fetch(FetchMode.JOIN)
  private Customer customer;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "entry_tag", joinColumns = @JoinColumn(name = "entry_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
  @JsonIgnoreProperties("entries")
  private Set<Tag> tags;

  public Long getId() {
    return id;
  }

  public LocalDateTime getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(LocalDateTime checkIn) {
    this.checkIn = checkIn;
  }

  public LocalDateTime getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(LocalDateTime checkOut) {
    this.checkOut = checkOut;
  }

  public Category getCategory() {
    return category;
  }

  public Set<Tag> getTags() {
    return tags;
  }

  public void setTags(Set<Tag> tags) {
    this.tags = tags;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Customer getCustomer() {
    return this.customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }


  // ./mvnw quarkus:add-extension -Dextensions='hibernate-validator'
  @Schema(hidden = true)
  @AssertTrue(message = "check out should always be after check in")
  private boolean isCheckOutAfterCheckIn() {
    return this.checkOut.isAfter(this.checkIn);
  }
}
