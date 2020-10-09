package sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sfgpetclinic.model.Owner;
import sfgpetclinic.model.Pet;
import sfgpetclinic.model.PetType;
import sfgpetclinic.model.Vet;
import sfgpetclinic.services.OwnerService;
import sfgpetclinic.services.PetService;
import sfgpetclinic.services.PetTypeService;
import sfgpetclinic.services.VetService;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }


    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("cat");
        PetType savedCatType = petTypeService.save(cat);

       Owner owner1 = new Owner();
       owner1.setId(3L);
       owner1.setFirstName("Michael");
       owner1.setLastName("Weston");
       owner1.setAddress("123 abc");
       owner1.setCity("Cambodia");
       owner1.setTelephone("1235436546");
       ownerService.save(owner1);
       Pet aPet = new Pet();
       aPet.setPetType(savedDogType);
       aPet.setOwner(owner1);
       aPet.setBirthDate(LocalDate.now());
       aPet.setName("Pojo");
       owner1.getPets().add(aPet);

        Owner owner2 = new Owner();
       // owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 xyz");
        owner2.setCity("Cambodia");
        owner2.setTelephone("546324757");
        Pet aCat = new Pet();
        aCat.setName("Meow");
        aCat.setOwner(owner2);
        aCat.setBirthDate(LocalDate.now());
        aCat.setPetType(savedCatType);
        owner2.getPets().add(aCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners ....");

        Vet vet1 = new Vet();
      //  vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
      //  vet2.setId(2L);
        vet2.setFirstName("James");
        vet2.setLastName("Curry");
        vetService.save(vet2);

        System.out.println("Loaded Vets ....");
    }
}
