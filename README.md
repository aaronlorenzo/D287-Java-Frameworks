<strong>** DO NOT DISTRIBUTE OR PUBLICLY POST SOLUTIONS TO THESE LABS. MAKE ALL FORKS OF THIS REPOSITORY WITH SOLUTION CODE PRIVATE. PLEASE REFER TO THE STUDENT CODE OF CONDUCT AND ETHICAL EXPECTATIONS FOR COLLEGE OF INFORMATION TECHNOLOGY STUDENTS FOR SPECIFICS. ** </strong>

# WESTERN GOVERNOR UNIVERSITY 

## D287 – JAVA FRAMEWORKS

C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.
file name: mainscreen.html
 Line 13-102- Added CSS styling of page (background color, color, margin, etc)
 Line 104-Title and Header: Changed the shop name to "Computer Parts Emporium".
 Line 110-Header Image: Added a header image specific to computer parts.
 Line 114-Section Headers: Updated "Parts" to "Individual Components" 
 Line 146-and "Products" to "Custom Builds".
 Line 182-184--Footer: Added a footer with the business name and copyright date.



D.  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.
file name: mainscreen.html
Line 112-Added an "About" button. <a href="/about" class="btn btn-info">About Computer Parts Emporium</a>

file name: MainScreenController.java
Lines 53-56- Added a new mapping for the "About" page.

file name: about.html
Lines 1 - 128: Created a new "About" page with company information and navigation back to the main screen, with HTML layout and styling copied from mainscreen.html styling.


E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.

Changes Made in BootstrapData.java line 36-159:

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


G.  Modify the parts to track maximum and minimum inventory by doing the following:
•  Add additional fields to the part entity for maximum and minimum inventory.
•  Modify the sample inventory to include the maximum and minimum fields.
•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
•  Rename the file the persistent storage is saved to.
•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.


H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.


I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.


J.  Remove the class files for any unused validators in order to clean your code.
