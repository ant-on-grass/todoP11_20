package com.todop11_20.common.domain.entity;


import com.todop11_20.auth.model.request.AuthRequestDto;
import com.todop11_20.common.domain.annotation.FieldAnnotation;
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

  @FieldAnnotation("EMAIL")
  @Column(name = "email")
  private String email;

  @FieldAnnotation("PASSWORD")
  @Column(name = "password")
  private String password;

//  @Column(name = "nickname")
//  private String nickName;

  @FieldAnnotation("USER_STATUS")
  @Column(name = "user_status")
  @Enumerated(EnumType.STRING)
  private UserRole userRole;

  @FieldAnnotation("IS_DELETE")
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
