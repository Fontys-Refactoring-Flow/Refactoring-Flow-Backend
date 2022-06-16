package com.refactoryflow.refactoryflowbackend.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CodeFile")
public class CodeFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String Name;
    private String Type;

    @Lob
    private byte[] Data;

    public CodeFile(String name, String type, byte[] data) {
        Name = name;
        Type = type;
        Data = data;
    }

    public static class JwtRequestModel implements Serializable {


        private static final long serialVersionUID = 2636936156391265891L;

        private String username;
        private String password;

        public JwtRequestModel() {
        }

        public JwtRequestModel(String username, String password) {
            super();
            this.username = username; this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
