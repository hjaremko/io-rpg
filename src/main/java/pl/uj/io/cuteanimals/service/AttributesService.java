package pl.uj.io.cuteanimals.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.uj.io.cuteanimals.model.Attributes;
import pl.uj.io.cuteanimals.repository.AttributesRepository;

@Service
public class AttributesService {
    private static final Logger logger = LoggerFactory.getLogger(AttributesService.class);
    private final AttributesRepository attributesRepository;

    @Autowired
    public AttributesService(AttributesRepository attributesRepository) {
        this.attributesRepository = attributesRepository;

        // Add dummy attributes to prevent game from crashing
        // TODO: remove
        var mieczAttr = new Attributes(1, 0, 1, 1, 0);
        var tarczaAttr = new Attributes(2, 0, 0, 1, 5);
        var czolgAttr = new Attributes(3, 0, 5, 3, 20);

        attributesRepository.saveAndFlush(mieczAttr);
        attributesRepository.saveAndFlush(tarczaAttr);
        attributesRepository.saveAndFlush(czolgAttr);
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
