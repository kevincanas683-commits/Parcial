package modelos;

public class EnvioAereo extends Envio {
    private double tarifaBaseKm = 5000; // más caro por km
    private double recargoPeso = 4000;  // recargo mayor por kg

    public EnvioAereo(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        return (getDistancia() * tarifaBaseKm) + (getPeso() * recargoPeso) + 50; // cargo fijo
    }
}
