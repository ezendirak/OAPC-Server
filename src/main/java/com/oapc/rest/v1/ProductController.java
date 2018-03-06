package com.oapc.rest.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.oapc.model.Producte;
import com.oapc.repo.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v3")
public class ProductController {

	private final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/colors")
    public List<String> getAllColors() {
    	List<Producte> producteList = new ArrayList<Producte>();
    	producteList = productRepository.findAll();
    	List<String> colors = new ArrayList<String>();
    	for (Producte producte : producteList) {
    		if (!colors.contains(producte.getColor())) {
    			colors.add(producte.getColor());
    		}
		}
        return colors;
    }
    
    @Transactional(readOnly = true)
    @GetMapping("/fromFiltro")
    public List<Producte> getAllColors2(@RequestParam(value = "color", required=false) String color, @RequestParam(value="diametre", required=false) Long diametre) {
    	Stream<Producte> producteStream = productRepository.findAllStream();
    	logger.info("Color: " + color + ", Diametre: "+ diametre);
    	
    	if (color != null) {
    		producteStream = producteStream.filter(x -> x.getColor().equals(color));	
    	}
    	if(diametre != null){
    		producteStream = producteStream.filter(x -> x.getDiametre().equals(diametre));
    	}
    	return producteStream.collect(Collectors.toList());
    }
    
    @GetMapping("/diametres")
    public List<Long> getAllDiametres() {
    	List<Producte> producteList = new ArrayList<Producte>();
    	producteList = productRepository.findAll();
    	List<Long> diametres = new ArrayList<Long>();
    	for (Producte producte : producteList) {
    		if (!diametres.contains(producte.getDiametre())) {
    			diametres.add(producte.getDiametre());
    		}
		}
        return diametres;
    }
    
    @GetMapping("/products")
    public List<Producte> getAllProducts() {
    	return productRepository.findAll();        
    }

//    @GetMapping("/fromFiltro/{color}")
//    public ResponseEntity<Note> getNoteById(@PathVariable(value = "color") String color) {
//    	
//        Note note = colorRepository.findOne(color);
//        if(note == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok().body(note);
//    }


}