/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Coordinates.java                                   :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/18 12:09:16 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/19 12:29:01 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.model.coordinates;

import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinates {
	@Min(0)
	private int x;
	@Min(0)
	private int y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
}