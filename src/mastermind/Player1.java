/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

/**
 *
 * @author user
 */
public abstract class Player1 {

	public abstract Code getAnswer();

	public abstract Response respond(Code guess);
}
