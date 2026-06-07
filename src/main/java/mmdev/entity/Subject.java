package mmdev.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "semester")
    private int semester;

    @OneToMany(mappedBy = "subject",fetch = FetchType.LAZY)
    private List<Material> materials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University university;

}
