package modelos;

public abstract class Envio {
    private String codigo;
    private String cliente;
    private double peso;     // peso del paquete
    private double distancia; // distancia del envío

    public Envio(String codigo, String cliente, double peso, double distancia) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.peso = peso;
        this.distancia = distancia;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public double getPeso() {
        return peso;
    }

    public double getDistancia() {
        return distancia;
    }

    // Polimórfico: cada tipo de envío calcula tarifa de forma diferente
    public abstract double calcularTarifa();


}
