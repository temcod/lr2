package ru.itlearn.SpringBootLr2.lr2.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    private String uid;
    private String operationUid;
    private String systemName;
    private String systemTime;
    private String source;
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;

    public boolean isValid() {
        return uid != null && !uid.isEmpty()
                && operationUid != null && !operationUid.isEmpty()
                && systemTime != null && !systemTime.isEmpty()
                && communicationId > 0;
    }
}
