package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_wanted_skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWantedSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wanted_skill_seq_gen")
    @SequenceGenerator(name = "wanted_skill_seq_gen", sequenceName = "wanted_skill_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    private String desiredLevel;
}