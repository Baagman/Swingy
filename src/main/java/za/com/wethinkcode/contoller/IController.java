/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   IController.java                                   :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/08/08 14:28:08 by tbaagman          #+#    #+#             */
/*   Updated: 2019/08/08 14:28:09 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.contoller;

import za.com.wethinkcode.model.characters.Hero;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IController {

	void PlayerInit() throws SQLException;
	void Move(String direction, int sizeOfMap);
	Hero createHero(ResultSet resultSet) throws SQLException;
	boolean GameInit();
}