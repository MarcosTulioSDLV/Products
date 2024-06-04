package com.springboot.products.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@OpenAPIDefinition(info = @Info(
        title = "Products Rest API",
        description = "This REST API is to manage products, customers and their invoices in a store, providing CRUD (Create, Read, Update, Delete) operations for that purpose."
        ,version = "1.0"
        ,contact = @Contact(
        name = "Marcos Soto",
        url = "https://ar.linkedin.com/in/marcos-tulio-soto-de-la-vega-8a6b9668",
        email = "mtsotodelavega@gmail.com"
        )
        ,license = @License(name = "GNU General Public License",url = "https://www.gnu.org/licenses/gpl-3.0.html")
        ))
public class OpenApiConfig {


}
