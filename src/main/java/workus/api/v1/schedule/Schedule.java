package workus.api.v1.schedule;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import workus.api.common.BaseEntity;
import workus.api.v1.common.Progress;

import javax.persistence.*;

@Getter
@Entity
@EqualsAndHashCode(callSuper = false)
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column
    private String description;

    @Column(length = 8)
    private String startDe;

    @Column(length = 8)
    private String endDe;

    @Column(nullable = false)
    private Progress progress;
}
