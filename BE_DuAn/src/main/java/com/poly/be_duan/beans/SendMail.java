package com.poly.be_duan.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMail {
    @NotBlank(message = "{SendMail.to.NotBlank}")
    @Email(message = "{SendMail.to.Email}")
    private String to;

    @NotBlank(message = "{SendMail.to.subject}")
    private String subject;

    @NotBlank(message = "{SendMail.to.content}")
    private String content;

    /**
     * Description của method là làm gì
     *
     * @param[name] [giải thích]: mô tả ý nghĩa và giải thích cách dùng
     * @return [giải thích]: Mô tả giá trị và về của nó
     */
    @Override
    public String toString() {
        return "SendMail [to=" + to + ", subject=" + subject + ", content=" + content + "]";
    }

}
