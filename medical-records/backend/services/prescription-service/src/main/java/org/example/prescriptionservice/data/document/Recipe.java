package org.example.prescriptionservice.data.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Recipe extends BaseDocument {
    private long doctorId;
    private long patientId;
    private long visitId;

    private String prescriptionCode;
    private LocalDate dateCreated;

    private List<MedicineItem> medicines;
}
