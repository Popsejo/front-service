package kdg.microservices.client.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kdg.microservices.client.model.Student;
import kdg.microservices.client.model.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;
import java.util.List;

@Service
public class StudentService {
    public List<Student> getStudents() throws IOException {
        String studentString = "http://localhost:9000/";
        String result = getResult(studentString);

        ObjectMapper mapper = new ObjectMapper();
        List<Student> students = mapper.readValue(result, new TypeReference<List<Student>>() {
        });
        return students;
    }


    public Student getStudent(int id, String accessToken) throws IOException {
        //can only be called by ADMIN (docent)
        String studentString = "http://localhost:9000/student/" + id;
        String result = getResultS(studentString,accessToken);
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(result, Student.class);

        return student;
    }

    public String getResultS(String request, String access_token) throws IOException {
        //Do the call
        URL url = new URL(request);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Authorization","Bearer "+access_token);
        con.setRequestMethod("GET");
        con.connect();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
    public String getResult(String request) throws IOException {
        //Do the call
        URL url = new URL(request);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }

    public Cookie postResult() throws IOException {
        String clientId = "clientapp";
        String secret = "thisissecret";
        String scope = "webclient";
        String grantType = "password";
        String clientCredentials = clientId + ":" + secret;
        try {
            URL url = new URL("http://localhost:8999/oauth/token?grant_type=password&username=docent&password=password&scope=webclient");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(clientCredentials.getBytes()));
            con.setRequestProperty("Authorization", basicAuth);
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            int responscode = con.getResponseCode();
            System.out.println(responscode);
            System.out.println(con.getContent());
            if (responscode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // print result
                System.out.println(response.toString());
                ObjectMapper om = new ObjectMapper();
                Token token = om.readValue(response.toString(),Token.class);
                Cookie cookie = new Cookie("access_token",token.getAccess_token());
                return cookie;
            } else {
                System.out.println("POST request not worked");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
