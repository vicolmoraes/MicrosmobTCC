package vitoria.apsapp;


public class Questao {
    int codigo;
    String enunciado;
    String alternativaA;
    String alternativaB;
    String alternativaC;
    String alternativaCerta;
    int assunto;

    public Questao(){

    }

    public Questao(int _codigo, String _enunciado, String _alternativaA, String _alternativaB, String _alternativaC, String _alternativaCerta, int _assunto){
        this.codigo = _codigo;
        this.enunciado = _enunciado;
        this.alternativaA = _alternativaA;
        this.alternativaB = _alternativaB;
        this.alternativaC = _alternativaC;
        this.alternativaCerta = _alternativaCerta;
        this.assunto = _assunto;
    }

    public Questao( String _enunciado, String _alternativaA, String _alternativaB, String _alternativaC, String _alternativaCerta, int _assunto){
        this.enunciado = _enunciado;
        this.alternativaA = _alternativaA;
        this.alternativaB = _alternativaB;
        this.alternativaC = _alternativaC;
        this.alternativaCerta = _alternativaCerta;
        this.assunto = _assunto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getAlternativaA() {
        return alternativaA;
    }

    public void setAlternativaA(String alternativaA) {
        this.alternativaA = alternativaA;
    }

    public String getAlternativaB() {
        return alternativaB;
    }

    public void setAlternativaB(String alternativaB) {
        this.alternativaB = alternativaB;
    }

    public String getAlternativaC() {
        return alternativaC;
    }

    public void setAlternativaC(String alternativaC) {
        this.alternativaC = alternativaC;
    }

    public String getAlternativaCerta() {
        return alternativaCerta;
    }

    public void setAlternativaCerta(String alternativaCerta) {
        this.alternativaCerta = alternativaCerta;
    }

    public int getAssunto() {
        return assunto;
    }

    public void setAssunto(int assunto) {
        this.assunto = assunto;
    }


}
