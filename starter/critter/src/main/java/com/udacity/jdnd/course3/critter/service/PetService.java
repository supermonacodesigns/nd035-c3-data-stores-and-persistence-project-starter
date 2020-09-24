package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerService customerService;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /* {C}reate */

    // https://knowledge.udacity.com/questions/320943
    public Pet savePet(Pet pet) {
        pet = petRepository.save(pet);
        customerService.addPetToCustomer(pet, pet.getCustomer());

        return pet;
    }

    /* {R}ead */
    public Pet getPet(Long id) {
        return petRepository.getOne(id);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(Long id) {
        return petRepository.getPetsByCustomer_Id(id);
    }

    /* {U}pdate */

    /* {D}elete */

}
