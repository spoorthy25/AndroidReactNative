import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

class HelloUser extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.title}>Hey! How are you?</Text>
        <Text style={styles.messageText}>{this.props.message_from_native}</Text>
      </View>
    )
  }
}
var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  title: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  messageText: {
      fontSize: 40,
      textAlign: 'center',
      color: 'red',
      margin: 30,
    },
});

AppRegistry.registerComponent('RNIntegration', () => HelloUser);