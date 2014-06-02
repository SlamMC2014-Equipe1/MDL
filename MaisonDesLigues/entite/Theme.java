package entite;

public class Theme {
	private Integer idAtelier;
	private Integer idTheme;
	private String libelleTheme;
	
	public Theme(Integer idAt, Integer idTh, String libTh) {
		idAtelier = idAt;
		idTheme = idTh;
		libelleTheme = libTh;
	}
	
	public String req_InsertTheme() {
		return "EXEC SP_INSERT_THEME @IDATELIER = " + this.idAtelier + " "
				+ "@IDTHEME = " + this.idTheme + " "
				+ "@LIBELLETHEME = " + this.libelleTheme + ";";
	}
}
