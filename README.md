<strong>** DO NOT DISTRIBUTE OR PUBLICLY POST SOLUTIONS TO THESE LABS. MAKE ALL FORKS OF THIS REPOSITORY WITH SOLUTION CODE PRIVATE. PLEASE REFER TO THE STUDENT CODE OF CONDUCT AND ETHICAL EXPECTATIONS FOR COLLEGE OF INFORMATION TECHNOLOGY STUDENTS FOR SPECIFICS. ** </strong>

# WESTERN GOVERNOR UNIVERSITY 

## D287 – JAVA FRAMEWORKS

C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.
file name: mainscreen.html
 Line 13-102- Added CSS styling of page (background color, color, margin, etc)
 Line 104-Title and Header: Changed the shop name to "Computer Parts Emporium".
 Line 110-Header Image: Added a header image specific to computer parts.
 Line 182-184--Footer: Added a footer with the business name and copyright date.



D.  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.
file name: mainscreen.html
Line 112-Added an "About" button. <a href="/about" class="btn btn-info">About Computer Parts Emporium</a>

file name: MainScreenController.java
Lines 53-56- Added a new mapping for the "About" page.

file name: about.html
Lines 1 - 128: Created a new "About" page with company information and navigation back to the main screen, with HTML layout and styling copied from mainscreen.html styling.


E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.

Changes Made in BootstrapData.java 
line 36-159:

Cleared Existing Data:

All records from InhousePart, OutsourcedPart, and Product repositories are deleted to ensure a clean slate.
Inhouse Parts Added:

CPU Cooler: ID 901, $30.00, Inventory: 10
Motherboard: ID 902, $150.00, Inventory: 8
Graphics Card: ID 903, $300.00, Inventory: 5
Power Supply: ID 904, $75.00, Inventory: 15
SSD Drive: ID 905, $120.00, Inventory: 20
These are saved in the InhousePartRepository.

Outsourced Parts Added:

RAM Module: Company: Corsair, ID 100, $80.00, Inventory: 25
HDD 1TB: Company: Seagate, ID 101, $50.00, Inventory: 30
SSD 512GB: Company: Western Digital, ID 102, $90.00, Inventory: 20
Gaming Mouse: Company: Logitech, ID 103, $40.00, Inventory: 15
Mechanical Keyboard: Company: Razer, ID 104, $120.00, Inventory: 10
These are saved in the OutsourcedPartRepository.

Products Added:

Gaming PC: $1200.00, Inventory: 5
Workstation PC: $1500.00, Inventory: 3
Home Theater PC: $800.00, Inventory: 4
Gaming Laptop: $1000.00, Inventory: 2
Home Server: $700.00, Inventory: 3
These are saved in the ProductRepository.

Output Statements:

Displays the number of products and parts in the database.
Prints details of all products and parts.


F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
•  The “Buy Now” button must be next to the buttons that update and delete products.
•  The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
•  Display a message that indicates the success or failure of a purchase.

File name: mainscreen.html
Line 177: Added a line to create "Buy Now" button next to Product Add/Delete interface.
<a th:href="@{/buyProduct(productID=${tempProduct.id})}" class="btn btn-primary btn-sm mb-3">Buy Now</a>

File name: BuyProductController.java
Lines 1-38: New Controller for "Buy Product" button on mainscreen.html:
package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BuyProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") Long theId, Model theModel) {
        Optional<Product> productToBuy = productRepository.findById(theId);

        if (productToBuy.isPresent()) { // Check if product in catalog
            Product product = productToBuy.get();

            if (product.getInv() > 0) { // Check if product still in stock
                product.setInv(product.getInv() - 1); // Decrement stock
                productRepository.save(product); // Save to product database

                return "/confirmbuysuccess"; // Successful purchase
            } else {
                return "/confirmbuyfailure"; // Purchase failed: out of stock
            }
        } else {
            return "/confirmbuyfailure"; // Purchase failed: product not found
        }
    }
}
File name: confirmbuysuccess.html
Lines 1-13: New code which displays "Your purchase was successful" and thanks in response to a successful purchase of a product.
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Purchase Successful</title>
</head>
<body>
    <h1>Your purchase was successful. We hope you enjoy your treats! Thanks for shopping with us!</h1>
    <a href="http://localhost:8080/">Link to Main Screen</a>
</body>
</html>


File name: confirmbuyfailure.html
Lines 1-14: New code which displays "Your purchase did not succeed. Product may be out of stock. Please try again or contact us for assistance."
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Failure to complete purchase</title>
</head>
<body>
    <h1>Your purchase did not succeed. Product may be out of stock. Please try again or contact us for assistance.</h1>
    <a href="http://localhost:8080/">Link to Main Screen</a>
</body>
</html>


G.  Modify the parts to track maximum and minimum inventory by doing the following:
•  Add additional fields to the part entity for maximum and minimum inventory.
•  Modify the sample inventory to include the maximum and minimum fields.
•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
•  Rename the file the persistent storage is saved to.
•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.

File Name: InhousePart.java
Lines 18-19:
Change:
Added fields for minInv (minimum inventory) and maxInv (maximum inventory) to the InhousePart entity.

File Name: InhousePartServiceImpl.java
Lines 35-49:
Change:
Added a validateInventory method to ensure that minimum and maximum inventory values are non-negative and that minInv is less than or equal to maxInv.

File Name: BootstrapData.java
Lines 146-150:
Change:
Updated the sample data to include minInv and maxInv for each part.

File Name: InhousePartForm.html

Lines 25-32:
Change:
Added input fields for minInv and maxInv to the form for adding and editing InhouseParts.

File Name: OutsourcedPartForm.html (Similar changes)

File Name: application.properties
Line 6:
Change:
Renamed the persistent storage file to reflect the new schema.
"spring.datasource.url=jdbc:h2:file:~/computer-parts-db"


H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.

1. Minimum Inventory Validation

Custom Error Handling: Implemented a validation mechanism that triggers an error when the inventory for a part falls below its designated minimum threshold.

New Files Added:

ValidMinimum.java:
Line 1-23: package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MinimumValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMinimum {
String message() default "Part count falls below set minimum";
Class<?>[] groups() default {};
Class<? extends Payload>[] payload() default {};
}
MinimumValidator.java:
Line 1-28: package com.example.demo.validators;

import com.example.demo.domain.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinimumValidator implements ConstraintValidator<ValidMinimum, Part> {
@Autowired
private ApplicationContext context;
public static ApplicationContext myContext;

    @Override
    public void initialize(ValidMinimum constraintAnnotation) {
        // No specific initialization required
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        return part.getInv() > part.getMinInv();
    }
}

Modification in Part.java:
Line 4: import com.example.demo.validators.ValidMinimum;

@ValidMinimum

Product Validator Enhancement:

Updated EnufPartsValidator.java to include checks for parts inventory when updating product quantities. Added error messages to handle cases where parts’ inventory might be insufficient:
Line 19-29:
public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {
@Override
public boolean isValid(Product product, ConstraintValidatorContext context) {
// Validation logic with detailed error messages
}
}

Update in Product.java:
Line 3: import com.example.demo.validators.ValidEnufParts;

@ValidEnufParts

2. Maximum Inventory Validation

Custom Error Handling: Implemented validation to ensure inventory levels do not exceed the specified maximum, with appropriate error messages.

New Files Added:

ValidMaximum.java:

Line 1-24: package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MaximumValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMaximum {
String message() default "Part count is above set maximum";
Class<?>[] groups() default {};
Class<? extends Payload>[] payload() default {};
}

MaximumValidator.java:
Line 1-31: package com.example.demo.validators;

import com.example.demo.domain.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MaximumValidator implements ConstraintValidator<ValidMaximum, Part> {
@Autowired
private ApplicationContext context;

    @Override
    public void initialize(ValidMaximum constraintAnnotation) {
        // No specific initialization required
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        return part.getInv() <= part.getMaxInv();
    }
}
Modification in Part.java:
Line 3: import com.example.demo.validators.ValidMaximum;

@ValidMaximum

3. Error Display on Forms

Updates to HTML Forms:
InhousePartForm.html and OutsourcedPartForm.html: Incorporated error message display logic for inventory validation issues:
Line 93-96: <div th:if="${#fields.hasErrors()}">
<ul>
<li th:each="err : ${#fields.allErrors()}" th:text="${err}" class="error"/>
</ul>
</div>

I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.
File Name: PartTest.java
Line Number: 159-193
@Test
void getMinInv() {
int min_inv=0;
partIn.setMinInv(min_inv);
assertEquals(min_inv,partIn.getMinInv());
partOut.setMinInv(min_inv);
assertEquals(min_inv,partOut.getMinInv());
}

    @Test
    void setMinInv() {
        int min_inv=0;
        partIn.setMinInv(min_inv);
        assertEquals(min_inv,partIn.getMinInv());
        partOut.setMinInv(min_inv);
        assertEquals(min_inv,partOut.getMinInv());
    }

    @Test
    void getMaxInv() {
        int max_inv=101;
        partIn.setMaxInv(max_inv);
        assertEquals(max_inv,partIn.getMaxInv());
        partOut.setMaxInv(max_inv);
        assertEquals(max_inv,partOut.getMaxInv());
    }

    @Test
    void setMaxInv() {
        int max_inv=101;
        partIn.setMaxInv(max_inv);
        assertEquals(max_inv,partIn.getMaxInv());
        partOut.setMaxInv(max_inv);
        assertEquals(max_inv,partOut.getMaxInv());
    }
}
Change: Implemented unit tests to validate inventory constraints in PartTest.java.

J.  Remove the class files for any unused validators in order to clean your code.

DELETED unused validator:

filename: DeletePartValidator.java

and

filename: ValidDeletePart.java