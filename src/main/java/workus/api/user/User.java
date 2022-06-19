package workus.api.user;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import workus.api.common.BaseEntity;

import javax.persistence.*;
import java.util.UUID;

@Builder
@Getter
@Entity
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column
    private String password;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private UserStatus status;
}
