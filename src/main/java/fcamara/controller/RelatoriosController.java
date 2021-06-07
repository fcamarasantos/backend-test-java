package fcamara.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fcamara.controller.dto.RequisicaoRelatorioEstacionamentoDto;
import fcamara.controller.dto.RequisicaoRelatorioVeiculosDto;
import fcamara.model.entity.Estacionamento;
import fcamara.model.entity.Veiculo;
import fcamara.model.repository.EstacionamentoRepository;
import fcamara.model.repository.VeiculoRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("relatorios")
public class RelatoriosController {
	@Autowired
    private ResourceLoader resourceLoader;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private EstacionamentoRepository estacionamentoRepository;
	
    
    @GetMapping("/veiculos/porMarca")
    public ResponseEntity<byte[]> relatorioVeiculosPorMarca () throws IOException   
    {
        String path = resourceLoader.getResource("classpath:reports/relatorio_veiculos_por_marca.jasper").getURI().getPath();
        List<Veiculo> rs = veiculoRepository.findAll();
        byte[] contents;

        try { 
            JasperPrint jasperprint=null;
            List<RequisicaoRelatorioVeiculosDto> rel = new ArrayList<>();
            rs.forEach(item -> {
            	RequisicaoRelatorioVeiculosDto novo = new RequisicaoRelatorioVeiculosDto(item);
            	rel.add(novo);
            });
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(rel);
            jasperprint = JasperFillManager.fillReport(path, null, ds);
            contents=JasperExportManager.exportReportToPdf(jasperprint);

        } catch (JRException erro) {
            contents=null;
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }
    
    @GetMapping("/veiculos/listagem")
    public ResponseEntity<byte[]> relatorioVeiculosListagem () throws IOException   
    {
        String path = resourceLoader.getResource("classpath:reports/relatorio_veiculo_listar.jasper").getURI().getPath();
        List<Veiculo> rs = veiculoRepository.findAll();
        byte[] contents;

        try { 
            JasperPrint jasperprint=null;
            List<RequisicaoRelatorioVeiculosDto> rel = new ArrayList<>();
            rs.forEach(item -> {
            	RequisicaoRelatorioVeiculosDto novo = new RequisicaoRelatorioVeiculosDto(item);
            	rel.add(novo);
            });
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(rel);
            jasperprint = JasperFillManager.fillReport(path, null, ds);
            contents=JasperExportManager.exportReportToPdf(jasperprint);

        } catch (JRException erro) {
            contents=null;
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }
    
    @GetMapping("/estacionamentos/listagem")
    public ResponseEntity<byte[]> relatorioEstacionamentosListagem () throws IOException   
    {
        String path = resourceLoader.getResource("classpath:reports/relatorio_estacionamento_listar.jasper").getURI().getPath();
        List<Estacionamento> rs = estacionamentoRepository.findAll();
        byte[] contents;

        try { 
            JasperPrint jasperprint=null;
            List<RequisicaoRelatorioEstacionamentoDto> rel = new ArrayList<>();
            
            rs.forEach(item -> {
            	RequisicaoRelatorioEstacionamentoDto novo = new RequisicaoRelatorioEstacionamentoDto(item);
            	rel.add(novo);
            });
            
            
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(rel);
            
            jasperprint = JasperFillManager.fillReport(path, null, ds);
            contents=JasperExportManager.exportReportToPdf(jasperprint);

        } catch (JRException erro) {
            contents=null;
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }
    
}
