package contracts;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Configuration {
    private RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://demoqa.com").build();

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public void setRequestSpecification(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }
}
