package randomcontest;

public class NodeNetwork {

	private int layerCount;
	private NetLayer[] layers;

	public NodeNetwork(int[] nodes) {
		this.layerCount = nodes.length;
		this.setLayers(new NetLayer[layerCount]);
		
	}

	
	
	
	public int getLayerCount() {
		return layerCount;
	}

	public void setLayerCount(int layerCount) {
		this.layerCount = layerCount;
	}

	/**
	 * @return the layers
	 */
	public NetLayer[] getLayers() {
		return layers;
	}

	/**
	 * @param layers the layers to set
	 */
	public void setLayers(NetLayer[] layers) {
		this.layers = layers;
	}

}
