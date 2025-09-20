package modelos;

public class EnvioTerrestre extends Envio {

    private double tarifaBaseKm = 1500; // tarifa por km
    private double recargoPeso = 2000; // recargo por kg
    private final double velocidadKmH = 80; // propia de este medio

    public EnvioTerrestre(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        return (getDistancia() * tarifaBaseKm) + (getPeso() * recargoPeso);
    }
}
