package lv.venta.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {
	
	@Setter(value = AccessLevel.NONE) //No automatic set for ID
	private int id;
	
	@NotNull
	@Size(min = 3, max = 20)
	@Pattern(regexp = "[A-Z]{1}[a-z ]+")
	private String title;
	
	@NotNull
	@Size(min = 3, max = 200)
	@Pattern(regexp = "[A-Za-z .:!]+")
	private String description;
	
	@Min(0)
	@Max(10000)
	private float price;
	
	@Min(0)
	@Max(10000)
	private int quantity;
	
	private static int counter = 1;
	
	public void setID() {
		this.id = counter++;
	}

	public Product(@NotNull @Size(min = 3, max = 20) @Pattern(regexp = "[A-Z]{1}[a-z ]+") String title,
			@NotNull @Size(min = 3, max = 200) @Pattern(regexp = "[A-Za-z .:!]+") String description,
			@Min(0) @Max(10000) float price, @Min(0) @Max(10000) int quantity) {
		setID();
		this.title = title;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	
	
}
