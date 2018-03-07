package com.oapc.rest.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.oapc.model.Pdu;
import com.oapc.repo.PduRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v3")
public class PduController {

	private final Logger logger = LoggerFactory.getLogger(PduController.class);
	
    @Autowired
    PduRepository pduRepository;

    private String[] taules = {"FAMILIA", "PRODUCTE", "SUBFAMILIA", "GRUP", "SUBGRUP", "COLORCARN", "QUALITAT", "CALIBRE"};
    private String taulaTrobada;
    private String keyTrobada;
    
    @Transactional(readOnly = true)
    @GetMapping("/pdu/{datos}")
    public List<Pdu> getAllColors(@PathVariable(value = "datos") String datos) {
//    	List<Pdu> pduList = new ArrayList<Pdu>();
//    	final String datosIn = new String(datos);
    	logger.info("PDU: " + datos);
    	for (String pduTaula : taules) {
    		if (isContain(datos,pduTaula)) {
//    			String regex = "\\s*\\b"+pduTaula+"\\b\\s*";
    			datos = datos.replaceAll(pduTaula, "");
    			setTaulaTrobada(pduTaula);
    			setKeyTrobada(datos);
    			logger.info("Dins del for, taula: " + pduTaula);
    			logger.info("Dins del for, temp: " + datos);
    		}
		}
    	logger.info("PDU: " + datos);
    	//DATOS/TEMP ara nomes tenim la key(clave)
    	Stream<Pdu> pduStream = pduRepository.findAllStream();
//    	pduStream = pduStream.filter(x -> x.getTabla().equals(getTaulaTrobada())).filter(x -> x.getClave().equals(getKeyTrobada()));
    	pduStream = pduStream.filter(x -> x.getTabla().equals(getTaulaTrobada())).filter(x -> x.getClave().contains(getKeyTrobada()));
        return pduStream.collect(Collectors.toList());                     
    }
    
    private static boolean isContain(String source, String subItem){
//        String pattern = "\\b"+subItem+"\\b";
        String sourceUnprocess = source;
        source = source.replaceAll(subItem, "");
        if(source.equals(sourceUnprocess)) {
        	return false;
        }else {
        	return true;
        }
   }

	public String getTaulaTrobada() {
		return taulaTrobada;
	}

	public void setTaulaTrobada(String taulaTrobada) {
		this.taulaTrobada = taulaTrobada;
	}

	public String getKeyTrobada() {
		return keyTrobada;
	}

	public void setKeyTrobada(String keyTrobada) {
		this.keyTrobada = keyTrobada;
	}
    
//    @Transactional(readOnly = true)
//    @GetMapping("/fromFiltro")
//    public List<Pdu> getAllColors2(@RequestParam(value = "color", required=false) String color, @RequestParam(value="diametre", required=false) Long diametre) {
//    	Stream<Pdu> producteStream = pduRepository.findAllStream();
//    	logger.info("Color: " + color + ", Diametre: "+ diametre);
//    	
//    	if (color != null) {
//    		producteStream = producteStream.filter(x -> x.getColor().equals(color));	
//    	}
//    	if(diametre != null){
//    		producteStream = producteStream.filter(x -> x.getDiametre().equals(diametre));
//    	}
//    	return producteStream.collect(Collectors.toList());
//    } 
    
//    @GetMapping("/products")
//    public List<Pdu> getAllProducts() {
//    	return pduRepository.findAll();        
//    }

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