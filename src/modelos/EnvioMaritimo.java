package modelos;

public class EnvioMaritimo extends Envio {
    private double tarifaBaseKm = 800; // económico por km
    private double recargoPeso = 1000; // recargo menor

    public EnvioMaritimo(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        double tarifa = (getDistancia() * tarifaBaseKm) + (getPeso() * recargoPeso);
        if (getPeso() > 1000) { // descuento para cargas muy grandes
            tarifa *= 0.9;
        }
        return tarifa;
    }
}
