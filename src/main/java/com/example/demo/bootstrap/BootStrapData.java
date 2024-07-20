package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    private final InhousePartRepository inhousePartRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository,
                         InhousePartRepository inhousePartRepository,
                         OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.inhousePartRepository = inhousePartRepository;
        this.outsourcedPartRepository = outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        // Clear existing data
        inhousePartRepository.deleteAll();
        outsourcedPartRepository.deleteAll();
        productRepository.deleteAll();

        // Adding Inhouse Parts
        Set<InhousePart> inhouseParts = new HashSet<>();
        InhousePart ip1 = new InhousePart();
        ip1.setId(901);
        ip1.setName("CPU Cooler");
        ip1.setPrice(30.00);
        ip1.setInv(10);
        ip1.setMinInv(1);
        ip1.setMaxInv(50);
        inhouseParts.add(ip1);

        InhousePart ip2 = new InhousePart();
        ip2.setId(902);
        ip2.setName("Motherboard");
        ip2.setPrice(150.00);
        ip2.setInv(8);
        ip2.setMinInv(1);
        ip2.setMaxInv(30);
        inhouseParts.add(ip2);

        InhousePart ip3 = new InhousePart();
        ip3.setId(903);
        ip3.setName("Graphics Card");
        ip3.setPrice(300.00);
        ip3.setInv(5);
        ip3.setMinInv(1);
        ip3.setMaxInv(20);
        inhouseParts.add(ip3);

        InhousePart ip4 = new InhousePart();
        ip4.setId(904);
        ip4.setName("Power Supply");
        ip4.setPrice(75.00);
        ip4.setInv(15);
        ip4.setMinInv(1);
        ip4.setMaxInv(40);
        inhouseParts.add(ip4);

        InhousePart ip5 = new InhousePart();
        ip5.setId(905);
        ip5.setName("SSD Drive");
        ip5.setPrice(120.00);
        ip5.setInv(20);
        ip5.setMinInv(1);
        ip5.setMaxInv(60);
        inhouseParts.add(ip5);

        inhousePartRepository.saveAll(inhouseParts);

        // Adding Outsourced Parts
        Set<OutsourcedPart> outsourcedParts = new HashSet<>();
        OutsourcedPart op1 = new OutsourcedPart();
        op1.setCompanyName("Corsair");
        op1.setName("RAM Module");
        op1.setInv(25);
        op1.setPrice(80.00);
        op1.setId(100L);
        op1.setMinInv(1);
        op1.setMaxInv(50);
        outsourcedParts.add(op1);

        OutsourcedPart op2 = new OutsourcedPart();
        op2.setCompanyName("Seagate");
        op2.setName("HDD 1TB");
        op2.setInv(30);
        op2.setPrice(50.00);
        op2.setId(101L);
        op2.setMinInv(1);
        op2.setMaxInv(70);
        outsourcedParts.add(op2);

        OutsourcedPart op3 = new OutsourcedPart();
        op3.setCompanyName("Western Digital");
        op3.setName("SSD 512GB");
        op3.setInv(20);
        op3.setPrice(90.00);
        op3.setId(102L);
        op3.setMinInv(1);
        op3.setMaxInv(60);
        outsourcedParts.add(op3);

        OutsourcedPart op4 = new OutsourcedPart();
        op4.setCompanyName("Logitech");
        op4.setName("Gaming Mouse");
        op4.setInv(15);
        op4.setPrice(40.00);
        op4.setId(103L);
        op4.setMinInv(1);
        op4.setMaxInv(50);
        outsourcedParts.add(op4);

        OutsourcedPart op5 = new OutsourcedPart();
        op5.setCompanyName("Razer");
        op5.setName("Mechanical Keyboard");
        op5.setInv(10);
        op5.setPrice(120.00);
        op5.setId(104L);
        op5.setMinInv(1);
        op5.setMaxInv(40);
        outsourcedParts.add(op5);

        outsourcedPartRepository.saveAll(outsourcedParts);

        // Adding Products
        Product gamingPC = new Product("Gaming PC", 1200.00, 5);
        Product workstationPC = new Product("Workstation PC", 1500.00, 3);
        Product htpc = new Product("Home Theater PC", 800.00, 4);
        Product laptop = new Product("Gaming Laptop", 1000.00, 2);
        Product server = new Product("Home Server", 700.00, 3);

        productRepository.save(gamingPC);
        productRepository.save(workstationPC);
        productRepository.save(htpc);
        productRepository.save(laptop);
        productRepository.save(server);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products: " + productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts: " + partRepository.count());
        System.out.println(partRepository.findAll());
    }
}
