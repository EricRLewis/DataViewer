import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;


/**
 * Project 1 assignment
 * Data model.
 * 
 * @author Eric Lewis
 * @version 1
 */
public class PR1Model extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private int[] selectedRows = null;
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();
	private ChangeEvent changeEvent = null;

	
	/**
	 * Creates an instance of PR1Model class.
	 */
	public PR1Model() {
		super();
		changeEvent = new ChangeEvent(this);
	}

	/**
	 * Gets all row (vertices) data from the model.
	 * The data is stored in a one-dimensional floating point array.
	 * The array elements are x and y coordinates, as follows:
	 * 
	 * array element 0: vertex 0, x coordinate
	 * array element 1: vertex 0, y coordinate
	 * ...
	 * array element 2*i: vertex i, x coordinate
	 * array element 2*i+1: vertex i, y coordinate
	 * ...
	 * 
	 * @return The array of vertices.
	 */
	public float[] getAllData() {
		int r = getRowCount();
		float[] data = new float[2 * r];
		for (int i = 0; i < r; i++) {
			data[2 * i] = Float.parseFloat((String) getValueAt(i, 0));
			data[2 * i + 1] = Float.parseFloat((String) getValueAt(i, 1));
		}
		return data;
	}
	
	/**
	 * Gets row (vertices) data of two specified columns from the model.
	 * The data is stored in a one-dimensional floating point array.
	 * The array elements are x and y coordinates, as follows:
	 * 
	 * array element 0: vertex 0, x coordinate
	 * array element 1: vertex 0, y coordinate
	 * ...
	 * array element 2*i: vertex i, x coordinate
	 * array element 2*i+1: vertex i, y coordinate
	 * ...
	 * 
	 * @param xCol is the index of the column you wish to use as X values
	 * @param yCol is the index of the column you wish to use as Y values
	 * 
	 * @return The array of vertices.
	 */
	public float[] getTwoColumnData(int xCol, int yCol){
		int r = getRowCount();
		float[] data = new float[2 * r];
		for(int i = 0; i < r; i++){
			data[2*i] = Float.parseFloat((String) getValueAt(i, xCol));
			data[2 * i + 1] = Float.parseFloat((String) getValueAt(i, yCol));
		}
		return data;
	}

	/**
	 * Gets the selected row (vertices) data from the model.
	 * The data is stored in a one-dimensional floating point array.
	 * The array elements are x and y coordinates, as follows:
	 * 
	 * array element 0: vertex 0, x coordinate
	 * array element 1: vertex 0, y coordinate
	 * ...
	 * array element 2*i: vertex i, x coordinate
	 * array element 2*i+1: vertex i, y coordinate
	 * ...
	 * 
	 * @return The array of vertices.
	 */
	public float[] getSelectedData() {
		int s = (selectedRows == null ? 0 : selectedRows.length);
		float[] data = new float[2 * s];
		if (s > 0) {
			int j = 0;
			for (int i = 0; i < s; i++) {
				data[j++] = Float.parseFloat((String) getValueAt(selectedRows[i], 0));
				data[j++] = Float.parseFloat((String) getValueAt(selectedRows[i], 1));
			}
		}
		return data;
	}
	
	/**
	 * Gets the selected row (vertices) data of two specified columns from the model.
	 * The data is stored in a one-dimensional floating point array.
	 * The array elements are x and y coordinates, as follows:
	 * 
	 * array element 0: vertex 0, x coordinate
	 * array element 1: vertex 0, y coordinate
	 * ...
	 * array element 2*i: vertex i, x coordinate
	 * array element 2*i+1: vertex i, y coordinate
	 * ...
	 * 
	 * @return The array of vertices.
	 */
	public float[] getSelectedTwoColumnData(int xCol, int yCol) {
		int s = (selectedRows == null ? 0 : selectedRows.length);
		float[] data = new float[2 * s];
		if (s > 0) {
			int j = 0;
			for (int i = 0; i < s; i++) {
				data[j++] = Float.parseFloat((String) getValueAt(selectedRows[i], xCol));
				data[j++] = Float.parseFloat((String) getValueAt(selectedRows[i], yCol));
			}
		}
		return data;
	}
	
	
	
	
	/**
	 * Sets the selected rows (vertices).
	 * If the new and old selection differ, a <code>ChangeEvent</code> event is fired.
	 * 
	 * @param r The array of selected rows (vertices) indices
	 */
	public void setSelectedRows(int[] r) {
		if (r == null) {
			if (selectedRows != null) {
				selectedRows = null;
				fireStateChanged();
			}
		}
		else {
			if (selectedRows != null) {
				if (Arrays.equals(selectedRows, r)) {
					return;
				}
			}
			selectedRows = r == null ? null : Arrays.copyOf(r, r.length);
			fireStateChanged();
		}
		
	}

	/**
	 * Adds the rows (vertices) specified by the input parameters to the existing selection.
	 * 
	 * @param xmin The minimum x value.
	 * @param xmax The maximum x value.
	 * @param ymin The minimum y value.
	 * @param ymax The maximum y value.
	 */
	public void addSelectedRows(float xmin, float xmax, float ymin, float ymax, int xCol, int yCol) {
		int r = getRowCount();
		int[] selected = new int[r];
		for (int i = 0; i < r; i++) {
			selected[i] = 0;
			float x = Float.parseFloat((String) getValueAt(i, xCol));
			float y = Float.parseFloat((String) getValueAt(i, yCol));
			if (x >= xmin && x <= xmax && y >= ymin && y <= ymax) {
				selected[i] = 1;
			}
		}
		if (selectedRows != null) {
			for (int i = 0; i < selectedRows.length; i++) {
				selected[selectedRows[i]] = 1;
			}
		}
		int count = 0;
		for (int i = 0; i < r; i++) {
			if (selected[i] > 0) {
				count++;
			}
		}
		int[] newSelected = new int[count];
		count = 0;
		for (int i = 0; i < r; i++) {
			if (selected[i] > 0) {
				newSelected[count++] = i;
			}
		}
		setSelectedRows(newSelected);
	}

	/**
	 * @return
	 */
	public int[] getSelectedRows() {
		return selectedRows;
	}

	/**
	 * @return
	 */
	public float getScaleX(int c) {
		float s = scale(c);
		if(Float.isInfinite(s)){
			return 1.0f;
		}
		return scale(c);
	}

	/**
	 * @return
	 */
	public float getScaleY(int c) {
		float s = scale(c);
		if(Float.isInfinite(s)){
			return 1;
		}
		return scale(c);
	}

	/**
	 * @return
	 */
	public float getTranslationX(int c) {
		return translate(c);
	}

	/**
	 * @return
	 */
	public float getTranslationY(int c) {
		float t = translate(c);
		return t;
	}

	/**
	 * @param c The column index.
	 * @return The scale value.
	 */
	private float scale(int c) {
		float s = 1.0f;
		float min = Float.MAX_VALUE;
		float max = Float.MIN_VALUE;
		for (int i = 0; i < getRowCount(); i++) {
			float f = Float.parseFloat((String) getValueAt(i, c));
			if (f > max) {
				max = f;
			}
			if (f < min) {
				min = f;
			}
		}
		if (min < max) {
			s = 1.6f / (max - min);
		}
		return s;
	}

	/**
	 * 
	 * @param c 
	 * @return
	 */
	private float translate(int c) {
		float t = 0.0f;
		float min = Float.MAX_VALUE;
		float max = Float.MIN_VALUE;
		for (int i = 0; i < getRowCount(); i++) {
			float f = Float.parseFloat((String) getValueAt(i, c));
			if (f > max) {
				max = f;
			}
			if (f < min) {
				min = f;
			}
		}
		if (min < max) {
			t = 0.8f - max * 1.6f / (max - min);
		}
		if(Float.compare(min, 0.0f) == 0 && Float.compare(max, (float) 1.4E-45) == 0){
			t = 0.0f;
		}
		return t;
	}

	/**
	 * Adds a change listener.
	 * 
	 * @param l change listener
	 */
	public void addChangeListener(ChangeListener l) {
		listeners.add(l);
	}

	/**
	 * Removes a change listener.
	 * 
	 * @param l change listener
	 */
	public void removeChangeListener(ChangeListener l) {
		listeners.remove(l);
	}

	/**
	 * Fires a <code>ChangeEvent</code> event.
	 */
	protected void fireStateChanged() {
		for (ChangeListener l : listeners) {
			l.stateChanged(changeEvent);
		}
	}

}