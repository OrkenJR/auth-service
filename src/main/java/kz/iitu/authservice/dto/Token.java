package kz.iitu.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "token")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Token {

    @Id
    public String id;

    public LocalDateTime dateTime;

    public String userId;
}