package sfgpetclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sfgpetclinic.model.Pet;
import sfgpetclinic.model.Vet;
import sfgpetclinic.repositories.OwnerRepository;
import sfgpetclinic.repositories.PetRepository;
import sfgpetclinic.repositories.PetTypeRepository;
import sfgpetclinic.repositories.VetRepository;
import sfgpetclinic.services.PetService;
import sfgpetclinic.services.PetService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")

public class PetSDJpaService implements PetService {
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;
    private final VetRepository vetRepository;

    public PetSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
                           PetTypeRepository petTypeRepository, VetRepository vetRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
        this.vetRepository = vetRepository;
    }
    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        Optional<Pet> optionalPet = petRepository.findById(aLong);
        return optionalPet.orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }
}
