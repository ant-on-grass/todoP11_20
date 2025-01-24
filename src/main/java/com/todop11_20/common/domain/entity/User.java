package com.todop11_20.common.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.todop11_20.auth.model.request.AuthRequestDto;
import com.todop11_20.common.domain.enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
public class User {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

//  @Column(name = "nickname")
//  private String nickName;

  @Column(name = "user_status")
  @Enumerated(EnumType.STRING)
  private UserRole userRole;

  private boolean isDelete = Boolean.FALSE;

  @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
  //@JsonManagedReference
  //@JsonIgnore
  private List<UserTodo> TodoUsers = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
  private List<UserSubTodo> SubTodoUsers = new ArrayList<>();

  public User(String email, String password, UserRole userRole, boolean isDelete) {
    this.email = email;
    this.password = password;
    this.userRole = userRole;
    this.isDelete = isDelete;
  }

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public static User createUser(AuthRequestDto requestDto) {
    return new User(requestDto.getEmail(),
                    requestDto.getPassword()
        );
  }


}
