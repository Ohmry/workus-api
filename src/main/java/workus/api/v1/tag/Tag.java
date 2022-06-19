package workus.api.v1.tag;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import workus.api.common.BaseEntity;

import javax.persistence.*;

@Getter
@Entity
@EqualsAndHashCode(callSuper = false)
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
