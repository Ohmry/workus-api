package workus.api.v1.task;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import workus.api.common.BaseEntity;
import workus.api.v1.common.Progress;

import javax.persistence.*;

@Getter
@Entity
@EqualsAndHashCode(callSuper = false)
public class Task extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 8)
    private String startDe;

    @Column(length = 8)
    private String endDe;

    @Column(nullable = false)
    private Progress progress;
}
