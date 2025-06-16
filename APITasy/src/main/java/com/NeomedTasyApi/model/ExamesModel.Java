package com.NeomedTasyApi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.*;

@Entity
@Data
public class ExamesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Stateable> stateable;

    @Embedded
    private Template template;

    @Embedded
    private SubTemplate subtemplate;

    @Embedded
    private Physician physician;

    @Embedded
    private Operator operator;

    @Embedded
    private Patient patient;

    @Embedded
    private Exam exam;

    @Embedded
    private MedicalReport medical_report;
}

@Entity
@Data
class Stateable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String state;
    private String timestamps;
}

@Embeddable
@Data
class Template {
    private String title;
}

@Embeddable
@Data
class SubTemplate {
    private String title;
}

@Embeddable
@Data
class Physician {
    private String name;
    @Embedded
    private CRM crm;
    private String email;
    private String phone_number;
    private String rqe;
    private String cpf;
    private String rg;
}

@Embeddable
@Data
class CRM {
    private int number;
    private String state;
}

@Embeddable
@Data
class Operator {
    private String name;
    private String cpf;
    private String email;
}

@Embeddable
@Data
class Patient {
    private String id;
    private String name;
    private String birth_dt;
    private String gender;
    private String integration_key;
    private boolean pacemaker;
    private String cardiologist_follow_up;
    private String height;
    private String weight;
}

@Embeddable
@Data
class Exam {
    private String code;
    private String created_at;
    private String breathlessness_description;
    private int breathlessness_intensity;
    private String chest_pain_description;
    private int chest_pain_intensity;
    private String request_reason;
    private String other_informations_or_results;

    @Embedded
    private SourceFile source_file;

    private boolean critical_exam;
    private String reason_for_pendency;

    @Embedded
    private RedoTemplate redo_template;
    @Embedded
    private ExamType exam_type;
    @Embedded
    private Unit unit;
}

@Embeddable
@Data
class SourceFile {
    // Assuming file is not stored for now
}

@Embeddable
@Data
class RedoTemplate {
    private String title;
}

@Embeddable
@Data
class ExamType {
    private String name;
    private String slug;
}

@Embeddable
@Data
class Unit {
    private String id;
    private String name;
}

@Embeddable
@Data
class MedicalReport {
    private String id;
    private String file_base64;
    private String content;
    private boolean replace_result;
}
