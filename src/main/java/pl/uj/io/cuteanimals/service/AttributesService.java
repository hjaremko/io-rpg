package pl.uj.io.cuteanimals.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.uj.io.cuteanimals.model.entity.Attributes;
import pl.uj.io.cuteanimals.repository.AttributesRepository;

@Service
public class AttributesService {
    private static final Logger logger = LoggerFactory.getLogger(AttributesService.class);
    private final AttributesRepository attributesRepository;

    @Autowired
    public AttributesService(AttributesRepository attributesRepository) {
        this.attributesRepository = attributesRepository;

        // Add dummy attributes to prevent game from crashing
        var swordAttr = new Attributes(1, 0, 1, 1, 0);
        var shieldAttr = new Attributes(2, 0, 0, 1, 5);
        var magicWandAttr = new Attributes(3, 0, 3, 1, 0);
        var bowAttr = new Attributes(4, 0, 2, 1, 0);
        var arrowAttr = new Attributes(5, 0, 1, 1, 0);
        var coinAttr = new Attributes(6, 0, 0, 0, 0);
        var amuletAttr = new Attributes(7, 0, 0, 5, 0);
        var torchAttr = new Attributes(8, 0, 1, 0, 0);
        var appleAttr = new Attributes(9, 30, 0, 1, 0);

        attributesRepository.saveAndFlush(swordAttr);
        attributesRepository.saveAndFlush(shieldAttr);
        attributesRepository.saveAndFlush(magicWandAttr);
        attributesRepository.saveAndFlush(bowAttr);
        attributesRepository.saveAndFlush(arrowAttr);
        attributesRepository.saveAndFlush(coinAttr);
        attributesRepository.saveAndFlush(amuletAttr);
        attributesRepository.saveAndFlush(torchAttr);
        attributesRepository.saveAndFlush(appleAttr);
    }

    public List<Attributes> getAllAttributes() {
        return attributesRepository.findAll();
    }

    public Attributes getAttributes(int id) {
        Optional<Attributes> attr = attributesRepository.findById(id);
        return attr.orElseThrow(
                () ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Unknown item with id " + id));
    }

    public void addAttributes(Attributes attributes) {
        attributesRepository.saveAndFlush(attributes);
    }
}
