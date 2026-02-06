package com.comman_project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommanProjectApplicationTests {

	@Test
	void contextLoads() {
	}









/*/*
 GET  IMAGE  
 */

/*
 * controller  :->
 * @GetMapping("/{imageName}/image")
	
	public ResponseEntity<Object> getImage(@PathVariable String imageName)
			throws NotFoundException, IOException {
		log.info("Fetching image ");
		byte[] image = productService.getImage(imageName);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
	}
	
	
	
 * 
 */


/*
 * image  service
 */


/*
 * String imageName = null;
		if (productDTO.getImage() != null && !productDTO.getImage().isEmpty()) {
			imageName = imageHelperServiceImpl.imageSave(productDTO.getImage(), "product");
		}
		
		 
		 
		 
	
@Override
	public byte[] getProductImage(String imageName) throws NotFoundException, IOException {
		log.info("inside getProductImage");
		return imageHelperServiceImpl.showImage(imageName, "product");
	}
		
 */

/*
 * Assert  image
 */

/*@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

	private final MessageByLocaleService messageByLocaleService;

	@Value("${file.upload-dir}")
	private String basePath;

	@Override
	public String imageSave(MultipartFile image, String subDir) throws IOException {
		if (image == null || image.isEmpty()) {
			throw new IllegalArgumentException("Image file is empty or null");
		}

		Path path = Paths.get(basePath, subDir).toAbsolutePath();

		if (Files.notExists(path)) {
			Files.createDirectories(path);
		}

		String oriName = image.getOriginalFilename();
		String extension = "";

		if (oriName != null && oriName.lastIndexOf(".") > 0) {
			extension = oriName.substring(oriName.lastIndexOf(".")).toLowerCase();
		}

		String imageName = UUID.randomUUID().toString() + extension;

		Path imagePath = path.resolve(imageName);

		Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

		return imageName;
	}


   

	@Override
	public byte[] showImage(String imageName, String subDir) throws NotFoundException, IOException {
		Path path = Paths.get(basePath, subDir);
		Path imagePath = path.resolve(imageName);
		if (!Files.exists(imagePath)) {
			throw new NotFoundException(
					messageByLocaleService.getMessage("image.not.exists", new Object[] { imageName }));
		} else {
			return Files.readAllBytes(imagePath);
		}
	}

}
*/








 
/*
 * csv  controller  :-
 * 
 * 
 */

/*
 * 	@PostMapping("/create")
	public ResponseEntity<Object> uploadeCsv(@ModelAttribute MultipartFile file)
			throws ValidationException, IOException, CsvException {
		if (file.isEmpty() || !file.getOriginalFilename().endsWith(".csv")) {
			return new GenericResponseHandlers.Builder().setMessage("please  uploade  proper  csv  file").create();
		}
		log.info("inside  create  csv file");
		String path = customerService.uploadeCSV(file);
		return new GenericResponseHandlers.Builder().setMessage(path).setStatus(HttpStatus.CREATED).create();
	}

	@GetMapping("/downloadecsv")
	public ResponseEntity<byte[]> exportAllCustomersCSV() {
		try {
			byte[] csvData = customerService.getAllCustomersAsCSV();

			return ResponseEntity.ok().contentType(MediaType.parseMediaType("text/csv")).body(csvData);

		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating CSV file".getBytes());
		}
	}

 */






/*
 *csv  helper 
 *
 */



/*
*
*
*@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	private final String uploadDir = "uploads";
    private final  MessageByLocaleService message;
	private final CustomerRepository customerRepository;

	@Override
	public String uploadeCSV(MultipartFile file) throws IOException, CsvException, ValidationException {
		 Path filePath = Paths.get(uploadDir, file.getOriginalFilename());

		try (InputStream in = file.getInputStream()) {
			Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
		}

		List<String[]> rows;
		try (InputStream is = Files.newInputStream(filePath);
				CSVReader reader = new CSVReader(new InputStreamReader(is))) {
			rows = reader.readAll();
		} catch (IOException | CsvException e) {
			throw new RuntimeException("CSV read error: " + e.getMessage(), e);
		}

		log.info("CSV rows count: {}", rows.size());
		rows.forEach(r -> log.info("Row data -> {}", Arrays.toString(r)));

		for (int i = 1; i < rows.size(); i++) {
			String[] row = rows.get(i);

			if (row == null || row.length < 2 || row[0].isBlank() || row[1].isBlank()) {
				log.warn("Skipping invalid row at line {} -> {}", i + 1, Arrays.toString(row));
				continue;
			}

			String customerName = row[0].trim();
			String customerEmail = row[1].trim();

			log.info("Processing row {} => Name: {}, Email: {}", i + 1, customerName, customerEmail);

			
			if (customerRepository.findByCustomerNameIgnoreCaseAndActive(customerName, true).isPresent()) {
				log.warn("Duplicate customer found: {}. Skipping insert.", customerName);
				continue;
			}

			
			Customer customer = new Customer();
			customer.setCustomerName(customerName);
			customer.setCustomerEmail(customerEmail);

			customerRepository.save(customer);
			log.info("Customer saved: {}", customer);
		}

		return message.getMessage("csv.uploaded", null);
	}

	
	

	@Override
	public byte[] getAllCustomersAsCSV() throws IOException {
	    log.info("Fetching all active customers and exporting to CSV");

	    	    List<Customer> customers = customerRepository.findAllByActive(true);

	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    OutputStreamWriter osw = new OutputStreamWriter(outputStream);
	    BufferedWriter writer = new BufferedWriter(osw);

	    // CSV header
	    writer.write("Id,CustomerName,CustomerEmail\n");

	    // CSV data
	    for (Customer customer : customers) {
	        writer.write(customer.getId() + "," +
	                     customer.getCustomerName() + "," +
	                     customer.getCustomerEmail() + "\n");
	    }

	    writer.flush();
	    return outputStream.toByteArray();
	}
*/



























}
